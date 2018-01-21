package com.thecherno.ld24.menu;

import com.thecherno.ld24.Game;
import com.thecherno.ld24.graphics.Screen;
import com.thecherno.ld24.input.InputHandler;
import com.thecherno.ld24.sound.Sound;

public class QuitMenu extends Menu {

	public QuitMenu(InputHandler input) {
		super(input);
		Game.pop = null;
		timer = 10;
	}

	int timer = 10;
	String[] options = { "Quit", "Main Menu" };
	int selected = 0;

	public void update() {
		if (timer > 0)
			timer--;

		if (input.down && timer == 0) {
			selected++;
			if (selected == 2)
				selected = 0;
			Sound.menucycle.play(false);
			timer = 10;
		}

		if (input.up && timer == 0) {
			selected--;
			if (selected == -1)
				selected = 1;
			Sound.menucycle.play(false);
			timer = 10;
		}

		if (selected < 0)
			selected = 0;
		if (selected > 1) {
			selected = 1;
		}

		if (selected == 0) {
			options[selected] = "> " + "Quit" + " <";
			if (input.use && timer == 0) {
				System.out.println("Quitting...");
				System.exit(0);
			}
		} else {
			options[0] = "Quit";
		}

		if (selected == 1) {
			options[selected] = "> " + "Main Menu" + " <";
			if (input.use && timer == 0) {
				Sound.menu.play(false);
				Game.menu = new MainMenu(input);
				Game.level.destroy();
				Game.setMenuLevel();
			}
		} else {
			options[1] = "Main Menu";
		}

	}

	public void render(Screen screen) {
		int col = 0xffffff;
		screen.renderText("Are you sure you want to quit?", 240 - 20, 100 + 3, 30, 1, 0);
		screen.renderText("Are you sure you want to quit?", 240 - 20, 100, 30, 1, col);
		for (int i = 0; i < options.length; i++) {
			if (i == 1) {
				screen.renderText(options[i], 300 + 3, 350 + i * 60 + 3, 50, 1, 0);
				screen.renderText(options[i], 300, 350 + i * 60, 50, 1, col);
			} else {
				screen.renderText(options[i], 300 + 3, 350 + i * 60 + 3, 50, 1, 0);
				screen.renderText(options[i], 300, 350 + i * 60, 50, 1, 0xff0000);
			}
		}
	}
}
