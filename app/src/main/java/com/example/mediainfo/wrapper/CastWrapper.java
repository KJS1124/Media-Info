package com.example.mediainfo.wrapper;

import com.example.mediainfo.models.Cast;

import java.util.List;

public class CastWrapper {
    List<Cast> cast;

    public CastWrapper() {
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }


    @Override
    public String toString() {
        return "CastWrapper{" +
                "cast=" + cast +
                '}';
    }
}
