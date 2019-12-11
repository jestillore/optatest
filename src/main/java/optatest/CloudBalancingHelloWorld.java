package optatest;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.ArrayList;
import java.util.List;

public class CloudBalancingHelloWorld {

    public static void main(String [] args) {
        // Build the Solver
        SolverFactory<CloudBalance> solverFactory = SolverFactory.createFromXmlResource("cloudBalancingSolverConfig.xml");
        Solver<CloudBalance> solver = solverFactory.buildSolver();

        // Load a problem with 400 computers and 1200 processes
        CloudBalance unsolvedCloudBalance = getUnsolvedBalance();

        // Solve the problem
        CloudBalance solvedCloudBalance = solver.solve(unsolvedCloudBalance);

        System.out.println("Score: " + solvedCloudBalance.getScore().getHardScore() + "," + solvedCloudBalance.getScore().getSoftScore());

        // Display the result
        System.out.println("\nSolved cloudBalance with 400 computers and 1200 processes:\n"
                + toDisplayString(solvedCloudBalance));
    }

    private static String toDisplayString(CloudBalance cloudBalance) {
        StringBuilder displayString = new StringBuilder();
        for (CloudProcess process : cloudBalance.getProcessList()) {
            CloudComputer computer = process.getComputer();
            displayString.append("  ").append(process.getName()).append(" -> ")
                    .append(computer == null ? null : computer.getName()).append("\n");
        }
        return displayString.toString();
    }

    private static CloudBalance getUnsolvedBalance() {
        CloudBalance cloudBalance = new CloudBalance();

        // populate computers
        int [][] computers = {
                // cpu, ram, name
                { 7, 6, 'X' },
                { 6, 6, 'Y' }
        };
        List<CloudComputer> computerList = new ArrayList<>();
        for (int [] computer : computers) {
            CloudComputer cloudComputer = new CloudComputer();
            cloudComputer.setCpuPower(computer[0]);
            cloudComputer.setMemory(computer[1]);
            cloudComputer.setName((char) computer[2]);
            computerList.add(cloudComputer);
        }
        cloudBalance.setComputerList(computerList);

        // populate processes
        int [][] processes = {
                // cpu, ram, name
                { 5, 5, 'A' },
                { 4, 3, 'B' },
                { 2, 3, 'C' },
                { 2, 1, 'D' }
        };
        List<CloudProcess> processList = new ArrayList<>();
        for (int [] process : processes) {
            CloudProcess cloudProcess = new CloudProcess();
            cloudProcess.setRequiredCpuPower(process[0]);
            cloudProcess.setRequiredMemory(process[1]);
            cloudProcess.setName((char) process[2]);
            processList.add(cloudProcess);
        }
        cloudBalance.setProcessList(processList);

        return cloudBalance;
    }

}
