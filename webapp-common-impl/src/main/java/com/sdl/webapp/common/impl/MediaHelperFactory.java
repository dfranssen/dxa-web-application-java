package com.sdl.webapp.common.impl;

import com.sdl.webapp.common.api.MediaHelper;
import com.sdl.webapp.common.api.WebRequestContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Arrays;

/**
 * Media helper factory. This factory bean will select and create the appropriate implementation of {@code MediaHelper}.
 */
@Component
public class MediaHelperFactory extends AbstractFactoryBean<MediaHelper> {

    // Class names of candidate implementations to try, ordered by priority (highest priority first)
    private static final String[] MEDIA_HELPER_CLASS_NAMES = {
            "com.sdl.webapp.cid.ContextualMediaHelper",
            "com.sdl.webapp.common.impl.DefaultMediaHelper"
    };

    private final WebRequestContext webRequestContext;

    @Autowired
    public MediaHelperFactory(WebRequestContext webRequestContext) {
        this.webRequestContext = webRequestContext;
    }

    @Override
    public Class<?> getObjectType() {
        return MediaHelper.class;
    }

    @Override
    protected MediaHelper createInstance() throws Exception {
        final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        for (String className : MEDIA_HELPER_CLASS_NAMES) {
            if (ClassUtils.isPresent(className, classLoader)) {
                return (MediaHelper) BeanUtils.instantiateClass(ClassUtils.getConstructorIfAvailable(
                        ClassUtils.forName(className, classLoader), WebRequestContext.class), webRequestContext);
            }
        }

        throw new BeanCreationException("Cannot create MediaHelper - none of the candidates is present: " +
                Arrays.toString(MEDIA_HELPER_CLASS_NAMES));
    }
}