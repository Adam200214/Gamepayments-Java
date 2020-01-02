package com.teamgames;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.teamgames.gamepayments.PlayerStore;
import com.teamgames.gamepayments.configuration.Configuration;
import com.teamgames.gamepayments.module.ConfigurationModule;
import com.teamgames.gamepayments.service.ConfigurationService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePayments {

	private static final Logger logger = Logger.getLogger(GamePayments.class.getName());

	private final PlayerStore playerStore;

	private static GamePayments instance;

	@Inject
	private GamePayments(PlayerStore playerStore) {
		this.playerStore = playerStore;
	}
	
	public static void main(String args[]) {
		try {
			logger.info("Initializing GamePayments...");

			Injector injector = Guice.createInjector(new ConfigurationModule());

			ConfigurationService configurationService = injector.getInstance(ConfigurationService.class);

			instance = injector.getInstance(GamePayments.class);

			logger.info(String.format("Finished initializing Teamgames API version %s", configurationService.getConfiguration().getVersion()));
		} catch (Throwable throwable) {
			logger.log(Level.SEVERE, "Failed to initialize GamePayments, something fatal occurred.", throwable);
		}
	}

	public static GamePayments getInstance() {
		if (instance == null) {
			throw new IllegalStateException("The instance is null. The reference either hasn't been initialized or was disposed of.");
		}
		return instance;
	}

}
