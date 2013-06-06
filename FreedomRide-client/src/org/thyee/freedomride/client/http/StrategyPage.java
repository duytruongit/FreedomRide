package org.thyee.freedomride.client.http;

/*
 * Copyright 2008-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;

import org.thyee.freedomride.client.entity.Strategy;

/**
 * A page is a sublist of a list of objects. It allows gain information about
 * the position of it in the containing entire list.
 * 
 * @param <T>
 * @author Oliver Gierke
 */
public class StrategyPage extends Page {

	private List<Strategy> content;

	public List<Strategy> getContent() {
		return content;
	}

	public void setContent(List<Strategy> content) {
		this.content = content;
	}

	boolean hasContent() {
		if (content != null && content.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

}
