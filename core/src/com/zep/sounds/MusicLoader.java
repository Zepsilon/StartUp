package com.zep.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicLoader {

	private static Music	music;
	private static Sound	fxRemove;
//	private static Sound fxAdd;

	public static void load() {
		System.out.println("Music loading..");
		music = Gdx.audio.newMusic(Gdx.files.internal("music/SpinCycle.mp3"));
		fxRemove = Gdx.audio.newSound(Gdx.files.internal("music/remove.mp3"));
	}

	public static void dispose() {
		if (music != null)
			music.dispose();
		music = null;
		if (fxRemove != null)
			fxRemove.dispose();
		fxRemove = null;
//		fxAdd.dispose();
	}

	public static Music getMusic() {
		return music;
	}

	public static void musicPlay(float volume, boolean loop) {
		if (music != null) {
			music.setVolume(volume); // sets the volume to half the maximum volume
			music.setLooping(loop); // will repeat playback until music.stop() is called
			music.play(); // resumes the playback
		}
	}

	/**
	 * isExtra parametresi true ise aynı anda birden cok satir ya da sutun silinmistir, ekstra effect gerekebilir
	 * 
	 * @param isExtra
	 * @return
	 */
	public static long fxRemove(boolean isExtra) {
		// TODO add exstra effect 
		if (fxRemove != null)
			return fxRemove.play();
		return 0;
	}

	public static void musicStop() {
		System.out.println("Music stopping..");
		if (music != null)
			music.stop(); // stops the playback
	}

	/**
	 * musigi efekt'li sekilde durdurur
	 * 
	 */
	public static void musicStopFx() {
		if (music != null)
			music.stop();
		// fx icin sound calistir
	}

	public static void musicPause() {
		music.pause(); // pauses the playback
	}
}
