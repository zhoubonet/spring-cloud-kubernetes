/*
 * Copyright 2013-2018 the original author or authors.
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
 *
 */

package org.springframework.cloud.kubernetes.leader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Gytis Trikleris
 */
@RunWith(MockitoJUnitRunner.class)
public class LeaderTest {

	private static final String ROLE = "test-role";

	private static final String ID = "test-id";

	@Mock
	private LeaderKubernetesHelper mockKubernetesHelper;

	private Leader leader;

	@Before
	public void before() {
		leader = new Leader(ROLE, ID, mockKubernetesHelper);
	}

	@Test
	public void shouldGetRole() {
		assertThat(leader.getRole()).isEqualTo(ROLE);
	}

	@Test
	public void shouldGetId() {
		assertThat(leader.getId()).isEqualTo(ID);
	}

	@Test
	public void shouldCheckValidity() {
		given(mockKubernetesHelper.podExists(ID)).willReturn(true);

		boolean result = leader.isValid();

		assertThat(result).isTrue();
		verify(mockKubernetesHelper).podExists(ID);
	}

}
