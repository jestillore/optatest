package optatest;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class CloudProcess {

    private int requiredCpuPower;
    private int requiredMemory;
    private int requiredNetworkBandwidth;
    private char name;

    private CloudComputer computer;

    public int getRequiredCpuPower() {
        return requiredCpuPower;
    }

    public void setRequiredCpuPower(int requiredCpuPower) {
        this.requiredCpuPower = requiredCpuPower;
    }

    public int getRequiredMemory() {
        return requiredMemory;
    }

    public void setRequiredMemory(int requiredMemory) {
        this.requiredMemory = requiredMemory;
    }

    public int getRequiredNetworkBandwidth() {
        return requiredNetworkBandwidth;
    }

    public void setRequiredNetworkBandwidth(int requiredNetworkBandwidth) {
        this.requiredNetworkBandwidth = requiredNetworkBandwidth;
    }

    @PlanningVariable(valueRangeProviderRefs = {"computerRange"})
    public CloudComputer getComputer() {
        return computer;
    }

    public void setComputer(CloudComputer computer) {
        this.computer = computer;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }
}
