package com.kodnest.tunehub.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.repository.PlaylistRepository;
import com.kodnest.tunehub.service.PlaylistService;

@Service
public class PlaylistServiceImp implements PlaylistService {

	@Autowired
	PlaylistRepository playlistRepository;

	@Override
	public void addPlaylist(Playlist playlist) {
		Playlist exiplaylist=playlistRepository.findByName(playlist.getName());
		if(exiplaylist==null) {
			playlistRepository.save(playlist);
		}
		else {
			System.out.println("Playlist already exists");
		}
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
	List<Playlist>playlist=playlistRepository.findAll();
		return playlist;
	}

}