package klu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import klu.model.Pets;
import klu.model.PetsManager;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*")
public class PetsController 
{
	@Autowired
	PetsManager JM;

	// Folder where images are saved
	private static final String UPLOAD_DIR = "uploads/";

	@PostMapping("/insert")
	public String insert(@RequestPart("pet") Pets pet, @RequestPart(value = "image", required = false) MultipartFile image) {
		try {
			if (image != null && !image.isEmpty()) {
				// Ensure upload directory exists
				File uploadDir = new File(UPLOAD_DIR);
				if (!uploadDir.exists()) uploadDir.mkdirs();

				// Save the file to uploads folder
				String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR, filename);
				Files.write(filePath, image.getBytes());

				// Set filename in pet object
				pet.setImageFilename(filename);
			}
			return JM.insertJob(pet);
		} catch (IOException e) {
			return "401::Image upload failed - " + e.getMessage();
		}
	}

	@GetMapping("/readpet")
	public String readPets() {
		return JM.readPets();
	}

	@GetMapping("/getpet/{id}")
	public String getPetById(@PathVariable("id") Long id) {
		return JM.getPetDetaisbyId(id);
	}

	@PutMapping("/update")
	public String updatePet(@RequestBody Pets pet) {
		return JM.updatePet(pet);
	}

	@DeleteMapping("/delete/{id}")
	public String deletePet(@PathVariable("id") Long id) {
		return JM.deletePet(id);
	}
}
