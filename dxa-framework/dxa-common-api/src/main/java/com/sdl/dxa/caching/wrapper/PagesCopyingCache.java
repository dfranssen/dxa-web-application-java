package com.sdl.dxa.caching.wrapper;

import com.sdl.dxa.api.datamodel.model.PageModelData;
import com.sdl.dxa.caching.LocalizationAwareCacheKey;
import com.sdl.webapp.common.api.model.PageModel;
import org.springframework.stereotype.Component;

@Component
public class PagesCopyingCache extends CopyingCache<PageModelData, PageModel> {

    @Override
    public String getCacheName() {
        return "pages";
    }

    @Override
    public Class<PageModel> getValueType() {
        return PageModel.class;
    }

    @Override
    public LocalizationAwareCacheKey getSpecificKey(PageModelData pageModelData, Object... keyParams) {
        return getKey(pageModelData.getUrlPath(), pageModelData.getMvcData());
    }

    @Override
    protected PageModel copy(PageModel value) {
        return value.deepCopy();
    }
}
