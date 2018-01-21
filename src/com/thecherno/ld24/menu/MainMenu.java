package com.thecherno.ld24.menu;

import com.thecherno.ld24.Game;
import com.thecherno.ld24.graphics.Screen;
import com.thecherno.ld24.input.InputHandler;
import com.thecherno.ld24.level.Level;
import com.thecherno.ld24.sound.Sound;

public class MainMenu extends Menu {

	public MainMenu(InputHandler input) {
		super(input);
	}

	int timer = 18;
	String[] options = { "Play", "Help", "About", "Quit" };
	int selected = 0;

	public void update() {
		if (timer > 0)
			timer--;
		if (input.down && timer == 0) {
			selected++;
			if (selected == 4)
				selected = 0;
			Sound.menucycle.play(false);
			timer = 10;
		}

		if (input.up && timer == 0) {
			selected--;
			if (selected == -1)
				selected = 3;
			Sound.menucycle.play(false);
			timer = 10;
		}

		if (selected < 0)
			selected = 0;
		if (selected > 3)
			selected = 3;

		if (selected == 0) {
			options[selected] = "> " + "Play" + " <";
		} else {
			options[0] = "Play";
		}

		if (selected == 1) {
			options[selected] = "> " + "Help" + " <";

			if (input.use) {
				Sound.menu.play(false);
				Game.menu = new HelpMenu(input);
			}
		} else {
			options[1] = "Help";
		}

		if (selected == 2) {
			options[selected] = "> " + "About" + " <";
			if (input.use) {
				Sound.menu.play(false);
				Game.menu = new AboutMenu(input);
			}
		} else {
			options[2] = "About ";
		}

		if (selected == 3) {
			options[selected] = "> " + "Quit" + " <";
			if (input.use) {
				Sound.menu.play(false);
				Game.menu = new QuitMenu(input);
			}
		} else {
			options[3] = "Quit ";
		}

		if (selected == 0 && input.use && timer == 0) {
			input.releaseAll();
			Sound.menutheme.stop();
			Sound.theme.play(false);
			Sound.start.play(false);
			Level.play = true;
			Game.menu = null;
			PlayMenu.biome = 2; // random.nextInt(3);
		}
	}

	public void render(Screen screen) {
		screen.renderText("Genesis", 170 + 4, 140 + 4, 120, 1, 0);
		screen.renderText("Genesis", 170, 140, 120, 1, 0xffffff);
		screen.renderText("A game by Yan Chernikov. Edited by Mattie.", 75 + 2, 188 + 2, 30, 1, 0);
		screen.renderText("A game by Yan Chernikov. Edited by Mattie.", 75, 188, 30, 1, 0xffffff);
		for (int i = 0; i < options.length; i++) {
			if (i != 3 && i != 0) {
				screen.renderText(options[i], 350 + 3, 300 + i * 60 + 3, 50, 1, 0);
				screen.renderText(options[i], 350, 300 + i * 60, 50, 1, 0xffffff);
			} else if (i == 3) {
				screen.renderText(options[i], 350 + 3, 300 + i * 60 + 3, 50, 1, 0);
				screen.renderText(options[i], 350, 300 + i * 60, 50, 1, 0xff0000);
			} else if (i == 0) {
				screen.renderText(options[i], 350 + 3, 300 + i * 60 + 3, 50, 1, 0);
				screen.renderText(options[i], 350, 300 + i * 60, 50, 1, 0x00ff00);
			}
		}
	}
}
