package com.zep.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicLoader {

	private static Music	music;
	private static Sound	fxRemove;
//	private static Sound fxAdd;

	public static void load() {
		music = Gdx.audio.newMusic(Gdx.files.internal("music/SpinCycle.mp3"));
		fxRemove = Gdx.audio.newSound(Gdx.files.internal("music/remove.mp3"));
	}

	public static void dispose() {
		music.dispose();
		fxRemove.dispose();
//		fxAdd.dispose();
	}

	public static Music getMusic() {
		return music;
	}

	public static void musicPlay(float volume, boolean loop) {
		music.setVolume(volume); // sets the volume to half the maximum volume
		music.setLooping(loop); // will repeat playback until music.stop() is called
		music.play(); // resumes the playback
	}

	/**
	 * isExtra parametresi true ise aynı anda birden cok satir ya da sutun silinmistir, ekstra effect gerekebilir
	 * @param isExtra
	 * @return
	 */
	public static long fxRemove(boolean isExtra) {
		// TODO add exstra effect 
		return fxRemove.play();
//		return 0;
	}

	public static void musicStop() {
		music.stop(); // stops the playback
	}

	/**
	 * musigi efekt'li sekilde durdurur
	 * 
	 */
	public static void musicStopFx() {
		music.stop();
		// fx icin sound calistir
	}

	public static void musicPause() {
		music.pause(); // pauses the playback
	}
}
