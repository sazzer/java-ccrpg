package uk.co.grahamcox.ccrpg.dao

/**
 * Exception thrown when a DAO call fails to find a particular record
 */
class NoRecordFoundException(msg: String = "") : Exception(msg)