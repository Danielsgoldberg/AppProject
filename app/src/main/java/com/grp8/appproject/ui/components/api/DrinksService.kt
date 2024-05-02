package com.grp8.appproject.ui.components.api

interface DrinksService
{
    suspend fun get(): List<Drink>

}