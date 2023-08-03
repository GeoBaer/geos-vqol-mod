package de.geobaer.gvqol;

import de.geobaer.gvqol.config.ConfigurationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static net.minecraft.server.command.CommandManager.literal;


public class Initializer implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("geo-vqol");
	public static final String[] FUN_FACTS = new String[] {
			"eggplants contain nicotine?",
			"honey doesn't spoil?",
			"stars eat planets?",
			"horses can't vomit?",
			"the most visited country is spain?",
			"You shouldn't drink Rainwater?",
			"a liter of water spread out over a square meter is only one millimeter tall?",
			"the word \"Puddle\" came from Latin?",
			"Heinz Ketchup Bottles always have the Number 57 on them?",
			"Canada is south of Detroit?",
			"bats are the only Mammal that can actually fly?",
			"polar bears have black skin?",
			"it's impossible to hum while holding your nose?",
			"the longest place name in the world is \"Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu\"?",
			"Sweden has 267 thousand Islands?"
	};

	@Override
	public void onInitialize() {
		// Yeah, if you read this, feel free to add some facts :)
		LOGGER.info("Initializing...");
		ConfigurationHandler.loadConfig();
		registerCommand();
		LOGGER.info("Did you know that " + FUN_FACTS[new Random().nextInt(FUN_FACTS.length)]);
	}

	private Text checkStatus(String name, boolean on) {
		return Text.literal((on ? "✓" : "❌") + " " + name).fillStyle(Style.EMPTY.withFormatting(on ? Formatting.GREEN : Formatting.RED));
	}

	private void registerCommand() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
			dispatcher.register(literal("gvqol")
					.then(literal("status").executes(context -> {
						context.getSource().sendMessage(checkStatus("Void Travel", ConfigurationHandler.isVoidTravel()));
						context.getSource().sendMessage(checkStatus("Chest Graves", ConfigurationHandler.isChestGraves()));
						context.getSource().sendMessage(checkStatus("Bow Looting", ConfigurationHandler.isBowLooting()));
						context.getSource().sendMessage(checkStatus("Anvil Cost", ConfigurationHandler.isAnvilCost()));
						context.getSource().sendMessage(checkStatus("Anvil Rename", ConfigurationHandler.isAnvilRename()));
						return 1;
					}))
					.then(literal("reload").requires(source -> source.hasPermissionLevel(4)).executes(context -> {
						ConfigurationHandler.loadConfig();
						context.getSource().sendFeedback(() -> Text.literal("Geo's VQoL Mod Reload successful.").fillStyle(Style.EMPTY.withFormatting(Formatting.GREEN)), true);
						return 1;
					})));
		});
	}

}