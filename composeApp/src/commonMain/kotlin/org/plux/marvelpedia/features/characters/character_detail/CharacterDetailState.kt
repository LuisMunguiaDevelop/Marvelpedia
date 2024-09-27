package org.plux.marvelpedia.features.characters.character_detail

import org.plux.marvelpedia.features.characters.character_list.model.Character
import org.plux.marvelpedia.features.comics.comic_list.model.Comic
import org.plux.marvelpedia.features.events.events_list.model.Event

data class CharacterDetailState(
    val character: Character = Character(),
    val comicList: List<Comic> = listOf(),
    val eventList: List<Event> = listOf(),
    var isLoadingComics: Boolean = false,
    var isLoadingEvents: Boolean = false,
)