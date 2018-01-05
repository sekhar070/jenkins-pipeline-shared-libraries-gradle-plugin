package com.mkobit.jenkins.pipelines

import com.mkobit.gradle.test.assertj.GradleAssertions.assertThat
import com.mkobit.gradle.test.kotlin.testkit.runner.buildWith
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.TestTemplate
import testsupport.ForGradleVersions
import testsupport.GradleProject

@ForGradleVersions
internal class UnitTestSourceIntegrationTest {

  @TestTemplate
  internal fun `can write unit tests using JenkinsPipelineUnit`(@GradleProject gradleRunner: GradleRunner) {
    val buildResult: BuildResult = gradleRunner.buildWith(
      arguments = listOf("test", "-i")
    )

    assertThat(buildResult)
      .hasTaskAtPathWithOutcome(":test", TaskOutcome.SUCCESS)
  }

  @TestTemplate
  internal fun `integrationTest task is not executed when test is executed`(@GradleProject gradleRunner: GradleRunner) {
    val buildResult: BuildResult = gradleRunner.buildWith(
      arguments = listOf("test", "-i")
    )

    assertThat(buildResult)
      .doesNotHaveTaskAtPath(":integrationTest")
      .hasTaskAtPath(":test")
  }
}
