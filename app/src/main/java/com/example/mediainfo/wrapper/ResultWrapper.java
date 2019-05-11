package com.example.mediainfo.wrapper;

import com.example.mediainfo.models.CardDetails;

import java.util.List;

public class ResultWrapper {
    private List<CardDetails> results;

    public ResultWrapper(List<CardDetails> results) {
        this.results = results;
    }

    public ResultWrapper() {
    }

    public List<CardDetails> getResults() {
        return results;
    }

    public void setResults(List<CardDetails> results) {
        this.results = results;
    }
}
