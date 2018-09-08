package com.holman.galaxy.model;

import com.google.common.base.Preconditions;

public class TranslationMovement {
    private Integer diaryDegrees;
    private TurningMode turningMode;

    public TranslationMovement(Integer diaryDegrees, TurningMode turningMode) {
        Preconditions.checkNotNull(diaryDegrees, "Diary degrees is required");
        Preconditions.checkNotNull(turningMode, "Turning mode is required");

        this.diaryDegrees = diaryDegrees;
        this.turningMode = turningMode;
    }

    public Integer getDiaryDegrees() {
        return diaryDegrees;
    }

    public TurningMode getTurningMode() {
        return turningMode;
    }

}
