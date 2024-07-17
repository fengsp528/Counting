# Word Counting

## Description
This program reads a plain text file and finds matches against a predefined set of words. The output displays the match count for each predefined word in a tabulated format.

## Features
Processes large text files up to 20 MB.
Handles up to 10,000 predefined words.
Case-insensitive word matching.
Only exact word matches, no substring matches.
Outputs results in a tabulated format.

## Requirements
Java Development Kit (JDK) 8 or later.
Input file: Plain text (ASCII) file with each record separated by a new line.
Predefined words file: Text file with each word separated by a new line. No duplicates. Maximum word length of 256 characters.
Sample Data
Input File

Line 1: Detecting first names is tricky to do even with AI.
Line 2: how do you say a street name is not a first name?
Predefined Words File
Name
Detect
AI

Output
Predefined word                Match count
Name                          2
AI                            1
Detect                        0

## Project Structure
Main class containing the core logic of the program.
processFile: Cleans the raw input file by removing non-letter characters and redundant spaces.
checkAllEnglishLetters: Validates that the cleaned file contains only English letters and spaces.
selectWords: Randomly selects words from the cleaned file to create the predefined words file.
calculateWordFrequencies: Calculates the match count for each predefined word in the cleaned file.
writeFrequenciesToFile: Writes the match count results to an output file.

## How to Use
Compile the Program

Open your terminal and navigate to the directory containing Solution.java. Compile and run the program. 

Ensure that the paths to your raw input file, cleaned file, predefined words file, and result file are correctly specified in the main method.

Process the Raw File

Uncomment the processFile method call to clean your raw input file.

Uncomment the checkAllEnglishLetters method call to validate the cleaned file.

Uncomment the selectWords method call to randomly select words from the cleaned file to create the predefined words file.

selectWords(pureFilePath, predefinedFilePath);
Calculate Word Frequencies and Output Results

Ensure the calculateWordFrequencies and writeFrequenciesToFile method calls are uncommented to calculate word frequencies and write the results to the output file.

## Customization
Adjust the file paths in the main method to match your local file system.
Modify the predefined words list to fit your specific use case.

