package clean.news.entity

import java.util.Date

data class User(
		val id: Long,
		val delay: Int,
		val created: Date,
		val karma: Int,
		val about: String,
		val submitted: List<Long>
)
