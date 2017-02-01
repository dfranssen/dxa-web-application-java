package com.sdl.dxa.api.model.data.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sdl.dxa.api.model.data.ContentModelData;
import com.sdl.dxa.api.model.data.EntityModelData;
import com.sdl.dxa.api.model.data.KeywordModelData;
import com.sdl.dxa.api.model.data.RichTextData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Wrapper for the polymorphic list JSON representation coming from .NET Template Builder.
 * <p>It serializes the list as the following
 * <pre><code>
 * {
 *     "list": {
 *         "$type": "ContentModelData[]",
 *         "$values": [
 *          { ... }, { ... }
 *         ]
 *     }
 * }
 * </code></pre>
 * meaning that all elements of a list are of type {@code {@link ContentModelData}}</p>
 * <p>For the known and excepted types of list subtypes are created so that type information is not expected on leaves.
 * In case of using the generic type, type information is added and expected on leaves by deserializer.</p>
 * <p>Unfortunately it's currently not possible to implement {@link List} interface since Jackson is handling this differently.
 * This is to be investigated and done in the future.</p>
 *
 * @param <T> type of elements of the list
 */
@Setter(value = AccessLevel.NONE)
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListWrapper<T> {

    @JsonProperty("$values")
    private List<T> values;

    public T get(int index) {
        return values.get(index);
    }

    public boolean empty() {
        return values.isEmpty();
    }

    /**
     * The concrete implementation of {@link ListWrapper} for {@link ContentModelData}.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonTypeName("ContentModelData[]")
    public static class ContentModelDataListWrapper extends ListWrapper<ContentModelData> {

        public ContentModelDataListWrapper(List<ContentModelData> values) {
            super(values);
        }
    }


    /**
     * The concrete implementation of {@link ListWrapper} for {@link ContentModelData}.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonTypeName("KeywordModelData[]")
    public static class KeywordModelDataListWrapper extends ListWrapper<KeywordModelData> {

        public KeywordModelDataListWrapper(List<KeywordModelData> values) {
            super(values);
        }
    }


    /**
     * The concrete implementation of {@link ListWrapper} for {@link EntityModelData}.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonTypeName("EntityModelData[]")
    public static class EntityModelDataListWrapper extends ListWrapper<EntityModelData> {

        public EntityModelDataListWrapper(List<EntityModelData> values) {
            super(values);
        }
    }

    /**
     * The concrete implementation of {@link ListWrapper} for {@link RichTextData}.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonTypeName("RichTextData[]")
    public static class RichTextFragmentListWrapper extends ListWrapper<RichTextData> {

        public RichTextFragmentListWrapper(List<RichTextData> values) {
            super(values);
        }
    }
}
