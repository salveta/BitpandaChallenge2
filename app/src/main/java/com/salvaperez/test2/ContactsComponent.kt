package com.salvaperez.test2

import Contact
import Time

fun main() {
    ContactsComponent().getRecentContacts()
}

class ContactsComponent {

    /**
     * in the live code this is set after the view loaded; you can assume that this won't be null or empty
     * you can mock or change this if you want
     */
    private var contacts: List<Contact>? = null
    private val totalContactShow = 3

    /**
     * todo : returned list must
     *  1. hold only unique entries (data NOT id)
     *  2. hold max three entries
     *  3. sorted by "last_used" (if you use a custom sort, i'd suggest to use the unix timestamp)
     */
    fun getRecentContacts(): List<Contact> {
        contacts = getEntries()

        val distinctContacts = contacts?.sortedByDescending { contact -> contact.last_used.date_iso8601 }?.distinctBy { contact -> contact.data }?.take(totalContactShow)

        distinctContacts?.let { contactsList ->
            return contactsList
        } ?: run {
             return emptyList()
        }
    }

    private fun getEntries(): List<Contact>  {
        return listOf(
            Contact(id = "1", data = "A", last_used = Time(date_iso8601 = "1614613048")),
            Contact(id = "2", data = "A", last_used = Time(date_iso8601 = "1614613058")),
            Contact(id = "3", data = "B", last_used = Time(date_iso8601 = "1614613068")),
            Contact(id = "4", data = "C", last_used = Time(date_iso8601 = "1614613178")),
            Contact(id = "5", data = "D", last_used = Time(date_iso8601 = "1614613098")),
            Contact(id = "6", data = "A", last_used = Time(date_iso8601 = "1614613099"))
        )
    }
}