class Vehicle {
    private String vrn;
    public String type;
    public String brand;
    private String repairStatus;

    Vehicle(String vrn, String type, String brand, String repairStatus) {
        this.vrn = vrn;
        this.type = type;
        this.brand = brand;
        this.repairStatus = repairStatus;
    }

    public String getVrn() {
        return this.vrn;
    }

    public String getType() {
        return this.type;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getRepairStatus() {
        return this.repairStatus;
    }

    public void setRepairStatus(String newRepairStatus) {
        this.repairStatus = newRepairStatus;
    }

    public String toString() {
        return "VRN: " + this.getVrn() + "; Brand: " + getBrand() + "; Type: " + getType() + "; Status: " + getRepairStatus();
    }
}
