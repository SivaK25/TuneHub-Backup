package com.kodnest.tunehub.service;

import java.util.List;

import com.kodnest.tunehub.entity.Song;

public interface SongService {

	void saveSong(Song song);

	boolean songExists(String name);

	List<Song> fetchAllSongs();

	void updateSong(Song song);

}