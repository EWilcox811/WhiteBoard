package edu.nu.jam.capstone.Requestsmodule;

/**
 * Interface for the response from the Back End Data.
 *
 */
public interface AsyncResponder {
    /**
     * This will automatically be called when running the proper get helper.
     * @param output
     * Output is the JSON response from the proper get helper. This is automatically supplied.
     */
    void processFinish(String output);
}
