/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.api;

import java.util.Set;

/**
 * A <code>Record</code> is a data envelope used to carry user data and associated metadata as it is processed by
 * a Pipeline.
 * <P/>
 * The {@link Header} of a <code>Record</code> is the associated metadata, some of which is generated by the Pipeline
 * runtime container.
 * <p/>
 * The data of a <code>Record</code> is a {@link Field} which can be a single value or a <code>Map</code> or
 * <code>List</code> data structure.
 * <p/>
 * The <code>Record</code> API supports a field-path expressions (a simplified
 * <a ref="http://en.wikipedia.org/wiki/XPath">XPath</a> like syntax) to directly access values within a
 * <code>Field</code> value data structure:
 * <p/>
 * <ul>
 *   <li>An empty string <code>""</code> refers to the root element of the <code>Record</code></li>
 *   <li>A <code>/{NAME}NAME</code> string refers to the '{NAME}' entry in a <code>Map></code></li>
 *   <li>A <code>[{INDEX}]</code>, with {INDEX} being a zero/positive integer, refers to the '{INDEX}' position in a
 *   <code>List</code></li>
 * </ul>
 * <p/>
 * A field-path expression can be used to reference a composite data structure of any depth and composition, for
 * example:
 * <p/>
 * <ul>
 *   <li>/contactInfo/firstName</li>
 *   <li>/contactInfo/lastName</li>
 *   <li>/contactInfo/email[0]</li>
 *   <li>/contactInfo/address/firstLine</li>
 *   <li>/contactInfo/address/secondLine</li>
 *   <li>/contactInfo/address/city</li>
 *   <li>/contactInfo/address/state</li>
 *   <li>/contactInfo/address/zip</li>
 *   <li>/contactInfo/phone[0]/number</li>
 *   <li>/contactInfo/phone[0]/type</li>
 *   <li>/contactInfo/phone[1]/number</li>
 *   <li>/contactInfo/phone[1]/type</li>
 * </ul>
 * </p>
 * Using field-path expressions is possible to check for existence, access, modify and delete a <code>Field</code> data
 * structure. The {@link #get(String)}, {@link #has(String)}, {@link #delete(String)} and {@link #getFieldPaths()}
 * methods work using field- path expressions.
 */
public interface Record {

  public interface Header {

    public String getStageCreator();

    public String getSourceId();

    public String getTrackingId();

    public String getPreviousStageTrackingId();

    public String getStagesPath();

    public byte[] getRaw();

    public String getRawMimeType();

    public Set<String> getAttributeNames();

    public String getAttribute(String name);

    public void setAttribute(String name, String value);

    public void deleteAttribute(String name);

  }

  public Header getHeader();

  public Field get();

  public Field set(Field field);

  public Field get(String fieldPath);

  public Field delete(String fieldPath);

  public boolean has(String fieldPath);

  public Set<String> getFieldPaths();

}
