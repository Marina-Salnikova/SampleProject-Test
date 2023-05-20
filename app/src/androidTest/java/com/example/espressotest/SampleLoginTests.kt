package com.example.espressotest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.espressotest.ui.login.LoginActivity
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


private const val testUserName = "test_name"
private const val correctTestPassword = "test_psw1234"
private const val incorrectTestPassword = "123"
private const val userNameError = "Not a valid username"
private const val passwordError = "Password must be >5 characters"

@RunWith(AndroidJUnit4::class)
class SampleLoginTests {
    @get:Rule
    val loginActivityRule = ActivityScenarioRule(LoginActivity::class.java)

    //login view objects
    private val usernameInput: ViewInteraction = onView(withId(R.id.username))
    private val passwordInput: ViewInteraction = onView(withId(R.id.password))
    private val loginButton: ViewInteraction = onView(withId(R.id.login))

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testSuccessfulLogin() {
        //fill in user name
        typeText(usernameInput, testUserName)

        //fill in password
        typeText(passwordInput, correctTestPassword)

        //click login
        loginButton.perform(click())

        //verify successful login: users list should be displayed
        onView(first(withId(R.id.imageview))).check(matches(isDisplayed()))
        onView(first(withId(R.id.textView))).check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyUserName() {
        //fill in password
        typeText(passwordInput, correctTestPassword)

        //leave user field empty
        usernameInput.perform(click())

        //verify error
        usernameInput.check(matches(hasErrorText(userNameError)))

        //verify button is disabled
        loginButton.check(matches(isNotEnabled()))
    }

    @Test
    fun testEmptyPassword() {
        //fill in user name
        typeText(usernameInput, testUserName)

        //leave password empty
        passwordInput.perform(click())

        //verify error
        passwordInput.check(matches(hasErrorText(passwordError)))

        //verify button is disabled
        loginButton.check(matches(isNotEnabled()))
    }

    @Test
    fun testIncorrectPassword() {
        //fill in user name
        typeText(usernameInput, testUserName)

        //add incorrect password
        typeText(passwordInput, incorrectTestPassword)

        //verify error
        passwordInput.check(matches(hasErrorText(passwordError)))

        //verify button is disabled
        loginButton.check(matches(isNotEnabled()))
    }

    @Test
    fun testEmptyLogin() {
        //validate that login button is disabled
        loginButton.check(matches(isNotEnabled()))

        //click through empty fields
        usernameInput.perform(click())
        passwordInput.perform(click())

        //validate that login button is still disabled
        loginButton.check(matches(isNotEnabled()))
    }

    private fun typeText(element: ViewInteraction, text: String) {
        element.perform(typeText(text))
    }

    private fun <T> first(matcher: Matcher<T>): Matcher<T> {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("Find first matching item")
            }
        }
    }
}