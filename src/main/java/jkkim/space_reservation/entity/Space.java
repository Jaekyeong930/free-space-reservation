package jkkim.space_reservation.entity;

import java.sql.Timestamp;

public class Space {
    private int     spaceId;
    private String  spaceArea;
    private int     spaceType;
    private String  spaceName;
    private String  spaceCapacity; // default : 1을 어떻게 설정할 지 고민해보기
    private boolean spaceStatus;
    private Timestamp spaceOperatingStart;
    private Timestamp spaceOperatingEnd;

    // getter
    public int getSpaceId() {
        return spaceId;
    }

    public String getSpaceArea() {
        return spaceArea;
    }

    public int getSpaceType() {
        return spaceType;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public String getSpaceCapacity() {
        return spaceCapacity;
    }

    public boolean isSpaceStatus() {
        return spaceStatus;
    }

    public Timestamp getSpaceOperatingStart() {
        return spaceOperatingStart;
    }

    public Timestamp getSpaceOperatingEnd() {
        return spaceOperatingEnd;
    }

    // setter
    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public void setSpaceArea(String spaceArea) {
        this.spaceArea = spaceArea;
    }

    public void setSpaceType(int spaceType) {
        this.spaceType = spaceType;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public void setSpaceCapacity(String spaceCapacity) {
        this.spaceCapacity = spaceCapacity;
    }

    public void setSpaceStatus(boolean spaceStatus) {
        this.spaceStatus = spaceStatus;
    }

    public void setSpaceOperatingStart(Timestamp spaceOperatingStart) {
        this.spaceOperatingStart = spaceOperatingStart;
    }

    public void setSpaceOperatingEnd(Timestamp spaceOperatingEnd) {
        this.spaceOperatingEnd = spaceOperatingEnd;
    }


}
