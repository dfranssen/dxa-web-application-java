/*
 * Copyright (c) 2015 SDL, Radagio & R. Oudshoorn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dd4t.contentmodel.impl;

import org.dd4t.contentmodel.Publication;

import java.io.Serializable;

public class PublicationImpl extends BaseItem implements Publication, Serializable {


    private static final long serialVersionUID = -8593904509879801004L;

    public PublicationImpl () {
        super();
    }

    public PublicationImpl (String id) {
        super();
        this.setId(id);
    }
}
