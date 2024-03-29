/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisma.opensource.timesheet.jbehave;

import org.jbehave.core.embedder.StoryRunner;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure(using =SeleniumConfiguration.class,pendingStepStrategy = FailingUponPendingStep.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = false, storyTimeoutInSecs = 100, threads = 1, metaFilters = "-skip")
@UsingSpring(resources = {"timesheet-steps.xml"})
public class AnnotatedTimeSheetStories  extends InjectableEmbedder {
 
    @Override
    @Test
    public void run() throws Throwable {
        CrossReference crossReference = new CrossReference().withJsonOnly().withOutputAfterEachStory(true).excludingStoriesWithNoExecutedScenarios(true);
        ContextView contextView = new LocalFrameContextView().sized(640, 120);
        SeleniumContext seleniumContext = new SeleniumContext();
        SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(contextView, seleniumContext,
                crossReference.getStepMonitor());
        Format[] formats = new Format[]{new SeleniumContextOutput(seleniumContext), CONSOLE, WEB_DRIVER_HTML};
 
        StoryReporterBuilder reporterBuilder = new StoryReporterBuilder().withCodeLocation(codeLocationFromClass(AnnotatedTimeSheetStories.class)).withFailureTrace(true).withFailureTraceCompression(true).withDefaultFormats().withFormats(formats).withCrossReference(crossReference);

        Configuration configuration = injectedEmbedder().configuration();
        configuration.useFailureStrategy(new FailingUponPendingStep()).useStoryControls(new StoryControls().doResetStateBeforeScenario(false)).useStepMonitor(stepMonitor).useStoryLoader(new LoadFromClasspath(AnnotatedTimeSheetStories.class)).useStoryReporterBuilder(reporterBuilder);

        if (configuration instanceof SeleniumConfiguration) {
            SeleniumConfiguration seleniumConfiguration = (SeleniumConfiguration) configuration;
            seleniumConfiguration.useSeleniumContext(seleniumContext);
        }
        injectedEmbedder().runStoriesAsPaths(storyPaths());
    }

    protected List<String> storyPaths() {
        List<String> list = asList("**/" + System.getProperty("storyFilter", "*") + ".story");
        String file = codeLocationFromClass(this.getClass()).getFile();
        return new StoryFinder().findPaths(file, list, null);
    }
}
