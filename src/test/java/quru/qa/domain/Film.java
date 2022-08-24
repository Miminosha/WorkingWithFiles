package quru.qa.domain;

public class Film {

    private String name;
    private Integer yearOfCreation;
    private String directedBy;
    private Integer runningTimeMinutes;
    private String language;
    private Starring starring;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public Integer getRunningTimeMinutes() {
        return runningTimeMinutes;
    }

    public void setRunningTimeMinutes(Integer runningTimeMinutes) {
        this.runningTimeMinutes = runningTimeMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Starring getStarring() {
        return starring;
    }

    public void setStarring(Starring starring) {
        this.starring = starring;
    }

    public static class Starring {
        public String getMainRole() {
            return mainRole;
        }

        public void setMainRole(String mainRole) {
            this.mainRole = mainRole;
        }

        public String getOthers() {
            return others;
        }

        public void setOthers(String others) {
            this.others = others;
        }

        private String mainRole;
        private String others;
    }
}
