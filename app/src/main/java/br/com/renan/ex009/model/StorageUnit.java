package br.com.renan.ex009.model;

public enum StorageUnit {
    BIT(1, null,  "bit"),
    BYTE(8, StorageUnit.BIT, "byte"),
    KB(1000, StorageUnit.BYTE, "kilobyte"),
    MB(1000, StorageUnit.KB, "megabyte"),
    GB(1000, StorageUnit.MB, "gigabyte"),
    TB(1000, StorageUnit.GB, "terabyte");


    private String description;
    private int timesBiggerThanPreviousUnit;
    private StorageUnit previous;

    public long convertBits(long value) {
        if(this.previous == null) {
            return value;
        }
        return this.previous.convertBits(value) / this.timesBiggerThanPreviousUnit;
    }

    public String getDescription() {
        return description;
    }

    public int getTimesBiggerThanPreviousUnit() {
        return timesBiggerThanPreviousUnit;
    }

    public static StorageUnit getStorageUnitByDescription(String description) {
        for(StorageUnit s : StorageUnit.values()) {
            if (description.equalsIgnoreCase(s.description)) {
                return s;
            }
        }
        return null;
    }

    StorageUnit(int timesBiggerThanPreviousUnit, StorageUnit previous, String description) {
        this.description = description;
        this.previous = previous;
        this.timesBiggerThanPreviousUnit = timesBiggerThanPreviousUnit;
    }

}
