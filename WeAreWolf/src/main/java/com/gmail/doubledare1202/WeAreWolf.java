package com.gmail.doubledare1202;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;


public class WeAreWolf extends JavaPlugin {

	private WolfCommands wolfExecutor;
	private WolfTabCompleter wolfTabCompleter;
	private Actions actions;
	private WolfUtils utils;
	//private CommandExecutor debugWolfExecutor;
	//private DebugActions debugActions;

	public Permission joinPermisson = new Permission("wearewolf.join");
	public Permission GMPermisson = new Permission("wearewolf.GM");
	public Permission debugPermisson = new Permission("weareWolf.debug");
	public File japaneseFile;
	public static FileConfiguration japanese;

	public PluginManager pm;

	public void onEnable() {
		actions = new Actions(this);
		utils = new WolfUtils(this);
		//getLogger().info("\u001b[00;31m" + "onEnableメソッドが呼び出されたよ！！" + "\u001b[00m");
		getLogger().info("Good Morning! Now, WeAreWolf enable!");
		// コンフィグをセーブする

		loadConfig();
		saveConfig();

		japaneseFile = new File(getDataFolder(), "japanese.yml");
		japanese = YamlConfiguration.loadConfiguration(japaneseFile);
		japanese();

		wolfExecutor = new WolfCommands(this, actions);
		getCommand("wearewolf").setExecutor(wolfExecutor);
		wolfTabCompleter = new WolfTabCompleter(this);
		getCommand("wearewolf").setTabCompleter(wolfTabCompleter);

		// GOOOOOOO Metrics
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}

	}

	@Override
	public void onDisable() {
		// TODO ここに、プラグインが無効化された時の処理を実装してください。
		getLogger().info("See you! I hope you are't killed by werewolf");
	}

	//コンフィグ製作
	private void loadConfig(){
		getConfig().addDefault("test", "true");
		getConfig().addDefault("language","japanese");
		getConfig().options().copyDefaults(true);
	}

	//日本語言語ファイル製作
	private void japanese() {
		//japanese.addDefault("test", "true");

		//wearewolf common announce
		japanese.addDefault("wolf_common_nogm", "%logo&fこのコマンドは&6ゲームマスターのみ&f実行可能です！");
		japanese.addDefault("wolf_common_commingsoon", "%logo&6Normalモード&fは未実装です。"
				+ "今後のアップデートをご期待ください。by&adualdare");
		japanese.addDefault("wolf_common_nogamerule", "%logo&6正しいゲームルール&fを入力してください！");
		japanese.addDefault("wolf_common_gamerule", "<gameRule>は&6Normal&f,&6OneNight&fから選んでください。");
		japanese.addDefault("wolf_common_nogs", "%logo&fこのコマンドは&6人狼ゲームを進行しているプレイヤー"
				+ "のみ&fが実行可能です。");
		japanese.addDefault("wolf_common_nocommand", "%logo&cあなたが入力したコマンドは定義されていません！"
				+ " &fTABキーを押して補完機能を使ってみましょう。");
		//wearewolf help Description
		japanese.addDefault("wolf_help_0", "%= %logo - help - %=");
		japanese.addDefault("wolf_help_1", "&e/wearewolf help &fこのコマンドです。");
		japanese.addDefault("wolf_help_2", "&e/wearewolf rule <gameRule> &f人狼ゲームのルールを表示します。<gameRule>OneNight,Normal");
		japanese.addDefault("wolf_help_3", "&e/wearewolf role <gameRule> &f各ゲームの割り振られる役職を表示します。");
		japanese.addDefault("wolf_help_4", "&e/wearewolf join &f人狼ゲームの待機部屋に入ります。");
		japanese.addDefault("wolf_help_5", "&e/wearewolf leave &f待機部屋から退出します。");
		japanese.addDefault("wolf_help_6", "&e/wearewolf whisper <player> &fプレイヤーにささやきを送ります。他のプレイヤーに見られることはありません。");
		japanese.addDefault("wolf_help_7", "&e/wearewolf kick <player> &fプレイヤーを待機部屋から退出させます。");
		japanese.addDefault("wolf_help_8", "&e/wearewolf joinGM &f人狼ゲームのゲームマスターになります。 /wr gameStartでゲームを開始できます。");
		japanese.addDefault("wolf_help_9", "&e/wearewolf gamestart <gameRule> <参加プレイヤー1> <参加プレイヤー2>...");
		japanese.addDefault("wolf_help_10", "&f人狼ゲームを開始します。ゲームルールと待機部屋にいるプレイヤーを任意に選択してゲームを開始できます。");
		//wearewolf rule Description
		japanese.addDefault("wolf_rule_onenight_0", "%= %logo - Rule -&aOneNight %=");
		japanese.addDefault("wolf_rule_onenight_1", "人狼ゲームを遊ぼうとすると最低8人ほどの人数が必要で、1ゲーム1時間ほどかかってしまう。"
				+ "ワンナイト人狼はそれを少人数で遊べて、ちゃんと人狼できちゃうお手軽なゲームです。");
		japanese.addDefault("wolf_rule_onenight_2", "&e-勝利条件-");
		japanese.addDefault("wolf_rule_onenight_3", "&b人間陣営 &f人狼を見つけ処刑すること。そして人間を"
				+ "誤って処刑しないこと。");
		japanese.addDefault("wolf_rule_onenight_4", "&c人狼陣営 &f人間をだまし処刑されないこと。そして人狼同士殺し合いをしないこと。");
		japanese.addDefault("wolf_rule_onenight_5", "&e-各役職の紹介-");
		japanese.addDefault("wolf_rule_onenight_6", "&a村人&f(人間陣営) ");
		japanese.addDefault("wolf_rule_onenight_7", "&f特殊な能力はありません。他の人に賛同しながら人狼を見つけましょう。");
		japanese.addDefault("wolf_rule_onenight_8", "&b占い師&f(人間陣営)");
		japanese.addDefault("wolf_rule_onenight_9", "&f他の人の役職をこっそり占うことができます。これは、村にとって大事な情報になります。");
		japanese.addDefault("wolf_rule_onenight_10", "&d怪盗&f(人間陣営)");
		japanese.addDefault("wolf_rule_onenight_11", "&f他の人の役職と自分の役職を交換できます。"
				+ "人間陣営と交換した場合は有利に働けますが、"
				+ "人狼と交換した場合その時から人狼として行動しなければなりません。");
		japanese.addDefault("wolf_rule_onenight_12", "&c人狼&f(人狼陣営)");
		japanese.addDefault("wolf_rule_onenight_13", "&f人間に正体がばれないように、人間に化けて、村を混乱させましょう。"
				+ "仲間の人狼がいる場合は協力してダマしましょう。");
		japanese.addDefault("wolf_rule_onenight_14", "&e-ゲームの流れ-");
		japanese.addDefault("wolf_rule_onenight_15", "&7【夜の時間】&b占い師&fと&d怪盗&fと&c人狼&fの人がこっそり行動する時間です。");
		japanese.addDefault("wolf_rule_onenight_16", "&b占い師&f 他のプレイヤー1人の役職を知る。もしくは、選ばれていない他の役職を知る"
				+ "のどちらかの行動をとることができます。");
		japanese.addDefault("wolf_rule_onenight_17", "&c人狼&f 仲間の人狼が誰かを知ることができます。もちろん人狼があなたひとりのときもあります。");
		japanese.addDefault("wolf_rule_onenight_18", "&d怪盗&f 自分の役職と他のプレイヤーの役職を交換することができます。(しなくてもよいです。)");
		japanese.addDefault("wolf_rule_onenight_19", "ですが、もし人狼になってしまった場合は、あなたも人狼として行動しなくてはなりません。");
		japanese.addDefault("wolf_rule_onenight_20", "&a村人はやることがないですが、targetコマンドを打つ必要はあります。");
		japanese.addDefault("wolf_rule_onenight_21", "全員の行動が終わったら昼の時間となります。");
		japanese.addDefault("wolf_rule_onenight_22", "&e【昼の時間】");
		japanese.addDefault("wolf_rule_onenight_23", "&f投票の時間となります。人間陣営は人狼をさがすために、"
				+ "人狼陣営は人間にバレないように話し合いをしましょう。");
		japanese.addDefault("wolf_rule_onenight_24", "話し合いの時間は、5~10分ほどがおすすめです。");
		japanese.addDefault("wolf_rule_onenight_25", "なお、このプラグインでは全員がvoteコマンドを実行することで昼のターンが終了します。");
		japanese.addDefault("wolf_rule_onenight_26", "&e【結果発表】");
		japanese.addDefault("wolf_rule_onenight_27", "&f各プレイヤーの投票をもとに勝敗を決めます。");
		japanese.addDefault("wolf_rule_onenight_28", "基本的には&e勝利条件&fのとおりですが、もしプレイヤーが全員&b人間陣営の場合は"
				+ "&a&o平和村&fとなり、全員で協力して全員の投票数を同じにしなくてはなりません。");
		//wearewolf role Description
		japanese.addDefault("wolf_role_onenight_0", "%= %logo - Role -&aOneNight %=");
		japanese.addDefault("wolf_role_onenight_1", "人数別に選ばれる候補となる役職を表示します");
		japanese.addDefault("wolf_role_onenight_2", "&e3人 &f村人1、占い師1、怪盗1、人狼2");
		japanese.addDefault("wolf_role_onenight_3", "&e4人 &f村人2、占い師1、怪盗1、人狼2");
		japanese.addDefault("wolf_role_onenight_4", "&e5人 &f村人3、占い師1、怪盗1、人狼2");
		japanese.addDefault("wolf_role_onenight_5", "&e6人以上8人未満 &f村人4、占い師1、怪盗1、人狼2");
		//wearewolf join
		japanese.addDefault("wolf_joingm_sucsess", "%logo&6%4&fは人狼ゲームのゲームマスターになりました。");
		japanese.addDefault("wolf_joingm_failed", "%logo&6%4&fはすでにゲームマスターになっています！");
		japanese.addDefault("wolf_join_sucsess", "%logo&6%4&fは人狼ゲームの待機部屋に入りました。");
		japanese.addDefault("wolf_join_failed", "%logo&6%4&fはすでに待機部屋に入っています！");
		//wearewolf leave
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_failed", "%logo&6%4&fは待機部屋にいません！");
		//wearewolf kick
		japanese.addDefault("wolf_kick_sucsess", "%logo&6%4&fを人狼ゲームの待機部屋からキックしました。");
		japanese.addDefault("wolf_kick_kicked", "%logo&fあなたは&6%4&fにより待機部屋からキックされました。");
		japanese.addDefault("wolf_kick_failed", "%logo&6%4&fは待機部屋にいません！");
		//wearewolf gamestart
		japanese.addDefault("wolf_gson_sucsess", "%logo&6%4&fがワンナイト人狼を開始しました。");
		japanese.addDefault("wolf_gson_numover", "%logo&6人数が多すぎます！&fワンナイト人狼はGMを含めて8人までです。");
		japanese.addDefault("wolf_gson_nojoin", "%logo&6指定したプレイヤー&fが待機部屋にいません！"
				+ " 参加プレイヤーを間違えてないか確認してください。");
		japanese.addDefault("wolf_gson_numfew", "%logo&6人数が足りません！&fワンナイト人狼は最低でも3人必要です。");
		//wearewolf whisper
		japanese.addDefault("wolf_whisper_listen", "%logo&6%4&fからささやきを受け取りました。&6");
		japanese.addDefault("wolf_whisper_sender", "%logo&6%4&fにささやきを送りました。&6");
		japanese.addDefault("wolf_whisper_nolistener", "%logo&6ささやきを送るプレイヤー&fを指定してください！");
		japanese.addDefault("wolf_whisper_nomsg", "%logo&6ささやくメッセージ&fを入力してください！");

		//wearewolf target
		japanese.addDefault("wolf_target_notarget", "%logo&6能力の使用先のプレイヤー&fを指定してください。");
		//wearewolf NightTurn Description
		//(on=OneNight,ni=NightTurn,ww=werewolf,ph=phantom,se=seer,vi=villager)
		japanese.addDefault("wolf_onni_first", "%= %logo - Rule - &aOneNight &e夜のターン %=");
		japanese.addDefault("wolf_onni_ability", "%= %logo - Rule - &aOneNight &b特殊能力 %=");
		japanese.addDefault("wolf_onniww_0", "あなたは&c人狼&fになりました。");
		japanese.addDefault("wolf_onniww_1", "役職 &c人狼 &c人狼陣営");
		japanese.addDefault("wolf_onniww_2", "勝利条件-他の人狼と協力し、人間に自分の正体をバレないようにする。");
		japanese.addDefault("wolf_onniww_3", "特殊能力-以心伝心 自分以外の人狼が誰なのかを知ることができます。");
		japanese.addDefault("wolf_onniww_4", "&e/wr target <player> &fで殺したいプレイヤーに殺意を向けましょう。");
		japanese.addDefault("wolf_onniww_5", "このコマンドに意味はありませんが、"
				+ "targetコマンドを打ったあとに特殊能力の結果を知ることができます。");
		japanese.addDefault("wolf_onniww_friend", "仲間の人狼は、&6");
		japanese.addDefault("wolf_onniww_desu", "&fです。");

		japanese.addDefault("wolf_onniph_0", "あなたは&d怪盗&fになりました。");
		japanese.addDefault("wolf_onniph_1", "役職 &d怪盗 &b人間陣営");
		japanese.addDefault("wolf_onniph_2", "勝利条件 人間と協力し、人狼の正体を暴くこと");
		japanese.addDefault("wolf_onniph_3", "特殊能力-心変わり 他のプレイヤー1人と自分の役職を入れ替えることができます。");
		japanese.addDefault("wolf_onniph_4", "&e/wr target <player> &fで入れ替えるプレイヤーを決めてください。"
				+ "(入れ替えない場合は自分の名前を指定してください)");
		japanese.addDefault("wolf_onniph_changeplayer", "あなたは&6%4&fと&6心を入れ替えました！");
		japanese.addDefault("wolf_onniph_yourrole", "あなたの役職は&6%4&fとなりました。");
		japanese.addDefault("wolf_onniph_alreadyability", "%logo&fあなたは既に&d怪盗の能力&fを使用しています！");

		japanese.addDefault("wolf_onnise_0", "あなたは&b占い師&fになりました。");
		japanese.addDefault("wolf_onnise_1", "役職&b占い師 人間陣営");
		japanese.addDefault("wolf_onnise_2", "勝利条件 人間と協力し、人狼の正体を暴くこと");
		japanese.addDefault("wolf_onnise_3", "特殊能力-占い 他のプレイヤー1人の役職を占いによって知ることができます。");
		japanese.addDefault("wolf_onnise_4", "&e/wr target <player> &fで占いたいプレイヤーを指定して占いましょう。");
		japanese.addDefault("wolf_onnise_divineresult", "&b[占い結果]&6%4&bの役職は&6%5&bです！");
		japanese.addDefault("wolf_onnise_alreadyability", "%logo&fあなたは既に&b占い師の能力&fを使用しています！");

		japanese.addDefault("wolf_onnivi_0", "あなたは&a村人&fになりました。");
		japanese.addDefault("wolf_onnivi_1", "役職&a村人 &b人間陣営");
		japanese.addDefault("wolf_onnivi_2", "勝利条件 人間と協力し、人狼の正体を暴くこと");
		japanese.addDefault("wolf_onnivi_3", "特殊能力-なし");
		japanese.addDefault("wolf_onnivi_4", "&e/wr target <player> &fで怪しいと思うプレイヤーに殺意を向けましょう");
		japanese.addDefault("wolf_onnivi_5", "*このコマンドに意味はありませんが、全員がtargetコマンドを実行することで"
				+ "昼のターンに移行します。");
		//wearewolf NoonTurn Description
		//(no=noon,ni=NightTurn,ww=werewolf,ph=phantom,se=seer,vi=villager)
		japanese.addDefault("wolf_onno_0", "%= %logo - Rule - &aOneNight &e昼のターン %=");
		japanese.addDefault("wolf_onno_1", "長い夜が明けました。全員目をあけてください。");
		japanese.addDefault("wolf_onno_2", "昼のターンです。それでは議論を始めます。");
		japanese.addDefault("wolf_onno_3", "&e/wr vote <player> &fで処刑したいプレイヤーに投票しましょう");
		japanese.addDefault("wolf_onno_4", "投票数が一番多いプレイヤーが処刑されます。"
				+ "（同票の場合は同票の人全員が処刑されます");
		//wearewolf vote
		japanese.addDefault("wolf_onno_youvote", "%logo&fあなたは&6%4&fに投票しました！");
		japanese.addDefault("wolf_onno_alreadyvote", "%logo&6あなたはもう投票しています！"
				+ "&f他プレイヤーの投票を待ちましょう。");
		//wearewolf result
		japanese.addDefault("wolf_result_first", "%= %logo - Rule - &aOneNight &e結果発表 %=");
		japanese.addDefault("wolf_result_peace", "&fこの村は&a平和村&fです！");
		japanese.addDefault("wolf_result_winww", "&c人狼陣営&fの勝ちです！おめでとうございます！");
		japanese.addDefault("wolf_result_winhuman", "&b人間陣営&fの勝ちです！おめでとうございます！");
		/*
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		japanese.addDefault("wolf_leave_sucsess", "%logo&6%4&fは待機部屋から退出しました。");
		*/

		japanese.options().copyDefaults(true);
		try {
			japanese.save(japaneseFile);
		} catch (IOException e) {
			getLogger().warning("Fialed to save 'jananese.yml'");
		}
	}
	/*死にました
	public String separateLanguage(String path){
		if(getConfig().getString("language").equals("japanese")){
			return japanese.getString(path);
		}
		return null;
	}
	 */

}
