package com.example.androidgithubapi.model;

data class UserRepository(
        val name: String = "",
        val updated_at: String = "",
        val stargazers_count: Int = 0,
        val language: String? = "",
        val html_url: String = ""
)