package com.gmail.doubledare1202;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

final public class WolfTabCompleter implements TabCompleter{

	private final WeAreWolf plugin;

	private final List<String> FIRST = ImmutableList.of("help","role","rule","join","leave"
			,"whisper","kick","joinGM","gameStart","target","vote");

	private final List<String> SECOND = ImmutableList.of("OneNight","Normal");

	public WolfTabCompleter(WeAreWolf plugin) {
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender paramCommandSender,
			Command paramCommand, String paramString,
			String[] args) {
		String lastArg = args[args.length - 1];

		if(args.length <= 1){
			return partial(args[0], FIRST);
		}else if(args.length == 2){
			String sub = args[0];
			if(sub.equalsIgnoreCase("rule") || sub.equalsIgnoreCase("role") || sub.equalsIgnoreCase("gameStart")){
				return partial(lastArg, SECOND);
			}
		}
		return null;
	}

	private List<String> partial(String token, Collection<String> from) {
		return StringUtil.copyPartialMatches(token, from, new ArrayList<String>(from.size()));
	}
}
