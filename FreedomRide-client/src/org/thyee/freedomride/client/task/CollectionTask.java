package org.thyee.freedomride.client.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thyee.freedomride.client.entity.Strategy;
import org.thyee.freedomride.client.entity.StrategyTemp;
import org.thyee.freedomride.client.view.base.PageActivity;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectionTask extends Thread {

	private Context context;
	private Map<String, String> map;

	public CollectionTask(Context context, Map<String, String> map) {
		this.context = context;
		this.map = map;
	}

	@Override
	public void run() {
		List<StrategyTemp> list = new ArrayList<StrategyTemp>();
		try {
			Set<String> set = map.keySet();
			ObjectMapper objectMapper = new ObjectMapper();
			for (String key : set) {
				// System.out.println(map.get(key));
				Strategy strategy = objectMapper.readValue(map.get(key),
						Strategy.class);
				StrategyTemp strategyTemp = new StrategyTemp();
				strategyTemp.setStrategy(strategy);
				strategyTemp.setKey(key);
				list.add(strategyTemp);
			}
			System.out.println("listsize" + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			((PageActivity) context).refreshFinish(list);
		}

		super.run();
	}
}
