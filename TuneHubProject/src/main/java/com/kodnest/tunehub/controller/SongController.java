package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;


@Controller
public class SongController {
	@Autowired
	SongService songService;

	@PostMapping("/addsongs")
	public String addsong(@ModelAttribute Song song) {
		String name=song.getName();
		boolean songExists=songService.songExists(name);
		if(songExists==false) {
			songService.saveSong(song);
		}
		else {
			System.out.println("duplicate entry");
		}
		return "adminhome";
	}

	@GetMapping("/playsongs")
	public String playSongs(Model model) {
		boolean premium=true;

		if(premium) {
			List<Song> songslist=songService.fetchAllSongs();
			model.addAttribute("songs",songslist);
			System.out.println(songslist);
			return "viewsongs";
		}
		else {
			return "pay";
		}
	}

	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		
		List<Song> songslist=songService.fetchAllSongs();
		model.addAttribute("songs",songslist);
		
		System.out.println(songslist);
		return "viewsongs";

	}
}