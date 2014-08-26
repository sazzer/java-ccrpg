package uk.co.grahamcox.ccrpg.authentication.external.google

/**
 * DAO to access Google Authentication configuration
 */
trait GoogleConfigDao {
    /**
     * Try and load the Google Authentication configuration
     * @return the configuration
     * @throws NoRecordFoundException if the record wasn't found
     */
    fun loadConfig(): Config;

}