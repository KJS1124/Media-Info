package com.example.mediainfo.wrapper;

import com.example.mediainfo.models.Video;

import java.util.List;

public class VideoWrapper {
    List<Video> results;

    public VideoWrapper() {
    }

    public VideoWrapper(List<Video> results) {
        this.results = results;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "VideoWrapper{" +
                "results=" + results +
                '}';
    }
}
