package com.example.mediainfo.wrapper;

import com.example.mediainfo.models.Episode;

import java.util.List;

public class EpisodeResult {
    List<Episode> episodes;

    public EpisodeResult() {
    }

    public EpisodeResult(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "EpisodeResult{" +
                "episodes=" + episodes +
                '}';
    }
}
