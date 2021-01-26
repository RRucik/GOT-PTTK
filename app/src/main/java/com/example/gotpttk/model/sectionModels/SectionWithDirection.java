package com.example.gotpttk.model.sectionModels;

import com.example.gotpttk.model.dbModels.Section;

public class SectionWithDirection {
    private Section section;
    private Boolean isReversed;

    public SectionWithDirection(Section section, Boolean isReversed){
        this.section = section;
        this.isReversed = isReversed;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Boolean getReversed() {
        return isReversed;
    }

    public void setReversed(Boolean reversed) {
        isReversed = reversed;
    }
}
