package com.company;

import java.util.Optional;

public abstract class Organization {

    private Position root;
    private String result;
    private Optional<Name> name;

    public Optional<Name> getName() {
        return name;
    }

    public void setName(Optional<Name> name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Organization() {
        root = createOrganization();
    }

    protected abstract Position createOrganization();

    /**
     * hire the given person as an employee in the position that has that title
     *
     * @param person
     * @param title
     * @return the newly filled position or empty if no position has that title
     */
    // the Optional is used if there may or may not be a null value
    public Optional<Position> findCurrentPosition(Position currPosition, String title) {
        // creates a Optional for the current hire
        Optional<Position> currHire = Optional.of(currPosition);
        // if the current employee with title return the current hire
        if (currHire.get().getTitle().equals(title)) {
            return currHire;
        } else {
            // loop through the current position for the direct reports
            for (Position p : currPosition.getDirectReports()) {
                //creating a temp variable for the findCurrentPosition to take in the position and title
                currHire = findCurrentPosition(p, title);
                // if the current position is present return the current hire
                if (currHire.isPresent()) {
                    return currHire;
                }
            }
        }
        return Optional.empty();
    }
    public Optional<Position> hire(Name person, String title) {
        // created a Optional with the findCurrentPosition to take root and title
        Optional<Position> currPosition = findCurrentPosition(root, title);
        // if the current position is there
        if(currPosition.isPresent()){
            Position p = currPosition.get();
            //If the position is not filled then return the employee with the updated position
            if(!p.isFilled()){
                currPosition.get().setEmployee(Optional.of(new Employee(person)));
            }
        }
                return Optional.empty();
            }



    @Override
    public String toString() {
        return printOrganization(root,"");
    }

    private  String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");

        for (Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "\t"));
        }
        return sb.toString();
    }



//    public Position getRoot() {
//        return root;
//    }
//
//    public void setRoot(Position root) {
//        this.root = root;
//    }
}
