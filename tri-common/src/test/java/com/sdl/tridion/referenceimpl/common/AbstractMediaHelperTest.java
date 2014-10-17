package com.sdl.tridion.referenceimpl.common;

import com.sdl.tridion.referenceimpl.common.config.ScreenWidth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AbstractMediaHelperTestConfig.class })
public class AbstractMediaHelperTest {

    @Autowired
    private AbstractMediaHelper mediaHelper;

    @Autowired
    private TestWebRequestContext webRequestContext;

    @Test
    public void testGetResponsiveWidthAbsoluteWidthFactor() {
        webRequestContext.setPixelRatio(1.0);
        assertThat(mediaHelper.getResponsiveWidth("50", 12), is(50));

        webRequestContext.setPixelRatio(1.5);
        assertThat(mediaHelper.getResponsiveWidth("50", 12), is(75));
    }

    @Test
    public void testGetResponsiveWidthExtraSmall() {
        webRequestContext.setScreenWidth(ScreenWidth.EXTRA_SMALL);
        webRequestContext.setMaxMediaWidth(1024);
        assertThat(mediaHelper.getResponsiveWidth("100%", 3), is(1024));
    }

    @Test
    public void testGetResponsiveWidthSmall() {
        webRequestContext.setScreenWidth(ScreenWidth.SMALL);
        webRequestContext.setMaxMediaWidth(1024);
        assertThat(mediaHelper.getResponsiveWidth("100%", 3), is(482));
    }

    @Test
    public void testGetResponsiveWidthMedium() {
        webRequestContext.setScreenWidth(ScreenWidth.MEDIUM);
        webRequestContext.setMaxMediaWidth(1024);
        assertThat(mediaHelper.getResponsiveWidth("100%", 3), is(166));
    }
}
