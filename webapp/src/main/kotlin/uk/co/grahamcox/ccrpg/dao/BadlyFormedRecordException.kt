package uk.co.grahamcox.ccrpg.dao

/**
 * Exception thrown when a DAO call finds a badly formed record
 */
class BadlyFormedRecordException(msg: String = "") : Exception(msg)