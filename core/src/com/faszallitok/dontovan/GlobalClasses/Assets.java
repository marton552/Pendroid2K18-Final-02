package com.faszallitok.dontovan.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;


public class Assets {
	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;



	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";


	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "alegreyaregular.otf";
		fontParameter.fontParameters.size = 30;
		fontParameter.fontParameters.borderColor = Color.WHITE;
		fontParameter.fontParameters.borderWidth = 1;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}

	public static final AssetDescriptor<BitmapFont> ALEGREYAREGULAR_FONT
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);


	//Atlasok
	//public static final AssetDescriptor<TextureAtlas> LOADING_ATLAS = new AssetDescriptor<TextureAtlas>("atlasok/title.atlas", TextureAtlas.class);

	//Penlogo
	public static final AssetDescriptor<Texture> PENLOGO = new AssetDescriptor<Texture>("penlogo.jpg", Texture.class);

	//Game
	public static final AssetDescriptor<Texture> PLAYER = new AssetDescriptor<Texture>("game/player.png", Texture.class);
	public static final AssetDescriptor<Texture> ENEMY = new AssetDescriptor<Texture>("game/enemy.png", Texture.class);
	public static final AssetDescriptor<Texture> ARENA = new AssetDescriptor<Texture>("game/arena.png", Texture.class);


	//BAR
	public static final AssetDescriptor<Texture> BAR = new AssetDescriptor<Texture>("game/bar.png", Texture.class);
	public static final AssetDescriptor<Texture> BAR_OUT = new AssetDescriptor<Texture>("game/bar-out.png", Texture.class);

	//Story
	public static final AssetDescriptor<Texture> STORY1 = new AssetDescriptor<Texture>("story/screen1.png", Texture.class);
	public static final AssetDescriptor<Texture> STORY2 = new AssetDescriptor<Texture>("story/screen2.png", Texture.class);
	public static final AssetDescriptor<Texture> STORY3 = new AssetDescriptor<Texture>("story/screen3.png", Texture.class);
	public static final AssetDescriptor<Texture> JOJO = new AssetDescriptor<Texture>("story/jojo.png", Texture.class);


	//Menu
	public static final AssetDescriptor<Texture> MENU_BG = new AssetDescriptor<Texture>("menu.png", Texture.class);
	public static final AssetDescriptor<Texture> MENU_ICON = new AssetDescriptor<Texture>("menu-icon.png", Texture.class);
	public static final AssetDescriptor<Texture> BG = new AssetDescriptor<Texture>("bg.png", Texture.class);

    //Button
	public static final AssetDescriptor<Texture> BTN_BACK = new AssetDescriptor<Texture>("ui_textures/btn_back.png", Texture.class);
	public static final AssetDescriptor<Texture> BTN_HOVER = new AssetDescriptor<Texture>("ui_textures/btn_hover.png", Texture.class);

	public static final AssetDescriptor<Texture> WHITE_TEXTURE = new AssetDescriptor<Texture>("ui_textures/white.png", Texture.class);
	public static final AssetDescriptor<Texture> FULLWHITE_TEXTURE = new AssetDescriptor<Texture>("ui_textures/fullwhite.png", Texture.class);

	//Slider
	public static final AssetDescriptor<Texture> SLIDER_BG = new AssetDescriptor<Texture>("ui_textures/slider.png", Texture.class);
	public static final AssetDescriptor<Texture> SLIDER_KNOB = new AssetDescriptor<Texture>("ui_textures/sliderknob.png", Texture.class);
	public static final AssetDescriptor<Texture> SLIDER_KNOBH = new AssetDescriptor<Texture>("ui_textures/sliderknob2.png", Texture.class);

	//Etc UI
	public static final AssetDescriptor<Texture> DARK = new AssetDescriptor<Texture>("ui_textures/dark.png", Texture.class);

	//wire
	public static final AssetDescriptor<Texture> WIRE0 = new AssetDescriptor<Texture>("wire/0.png", Texture.class);
	public static final AssetDescriptor<Texture> WIRE1 = new AssetDescriptor<Texture>("wire/1.png", Texture.class);
	public static final AssetDescriptor<Texture> WIRE2 = new AssetDescriptor<Texture>("wire/2.png", Texture.class);
	public static final AssetDescriptor<Texture> WIRE3 = new AssetDescriptor<Texture>("wire/3.png", Texture.class);
	public static final AssetDescriptor<Texture> WIRE4 = new AssetDescriptor<Texture>("wire/4.png", Texture.class);
	public static final AssetDescriptor<Texture> WIRE5 = new AssetDescriptor<Texture>("wire/5.png", Texture.class);
	public static final AssetDescriptor<Texture> WIRE6 = new AssetDescriptor<Texture>("wire/6.png", Texture.class);
	public static final AssetDescriptor<Texture> NYAKNYAK = new AssetDescriptor<Texture>("wire/nyaklap.png", Texture.class);
	public static final AssetDescriptor<Texture> START = new AssetDescriptor<Texture>("wire/start.png", Texture.class);
	public static final AssetDescriptor<Texture> END = new AssetDescriptor<Texture>("wire/End.png", Texture.class);


	//Sounds

	//Music
	public static final AssetDescriptor<Music> MENU_MUSIC = new AssetDescriptor<Music>("zene/1.ogg", Music.class);
	public static final AssetDescriptor<Music> GAME_MUSIC = new AssetDescriptor<Music>("zene/2.wav", Music.class);

	public static final AssetDescriptor<Music> MUSIC1 = new AssetDescriptor<Music>("story/story1.wav", Music.class);
	public static final AssetDescriptor<Music> MUSIC2 = new AssetDescriptor<Music>("story/story2.wav", Music.class);
	public static final AssetDescriptor<Music> MUSIC3 = new AssetDescriptor<Music>("story/story3.wav", Music.class);
	public static final AssetDescriptor<Music> MUSIC4 = new AssetDescriptor<Music>("story/story4.wav", Music.class);



	public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(PLAYER);
		manager.load(ENEMY);
		manager.load(ARENA);

		manager.load(BAR);
		manager.load(BAR_OUT);

		manager.load(MENU_BG);
		manager.load(MENU_ICON);
		manager.load(BG);

		manager.load(PENLOGO);

		manager.load(STORY1);
		manager.load(STORY2);
		manager.load(STORY3);
		manager.load(JOJO);

		manager.load(MUSIC1);
		manager.load(MUSIC2);
		manager.load(MUSIC3);
		manager.load(MUSIC4);

		manager.load(BTN_BACK);
		manager.load(BTN_HOVER);

		manager.load(WHITE_TEXTURE);
		manager.load(FULLWHITE_TEXTURE);

		manager.load(SLIDER_BG);
		manager.load(SLIDER_KNOB);
		manager.load(SLIDER_KNOBH);

		manager.load(WIRE0);
		manager.load(WIRE1);
		manager.load(WIRE2);
		manager.load(WIRE3);
		manager.load(WIRE4);
		manager.load(WIRE5);
		manager.load(WIRE6);
		manager.load(NYAKNYAK);
		manager.load(START);
		manager.load(END);

		manager.load(DARK);

		manager.load(MENU_MUSIC);
		manager.load(GAME_MUSIC);

		manager.load(ALEGREYAREGULAR_FONT);

	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
