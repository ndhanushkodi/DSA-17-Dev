# Wikipedia Search Engine Project

## Learning Goals
In this project, you will learn to:
* Use persistent storage in the form of Redis
* Make meaningful design decisions (and be able to defend them!) regarding the tools, given specifications/requirements
* Design own classes and their appropriate methods
* Learn and understand the functionality of the basic components of a search engine
* Write testable code, and test effectively

## Preparation
* Read about search engine [insert resources here]
* Set up Redis [insert instructions here]
* Set up Jsoup [do some practice?]

## Specifications
Your team is going to design a Wikipedia search engine that comprise of the
following components:
* *Crawler*: "crawls" from page to page via hyperlinks
* *Indexer*: keep track of which pages contain which search terms
* *SearchTool*: takes search terms and boolean operators and returns the most relevant results.
* *Persistent Storage*: Redis

### Crawler Requirements
* Your Crawler's job is to, given a page, locate and retrieve hyperlinks to other pages. It can keep doing this for as long as you want (you may choose to only look at that page, or all pages 1 hyperlinks away, or all pages 3 hyperlinks away). You decide based on how much time and space is required.
* A test class that verifies your Crawler's functionality.

### Indexer Requirements
* Saves and retrieves data to and from your Redis instance.
  * When saving, it saves a map of search terms to URLs containing those search terms. That is not all it's allowed to save, but that is the minimal.
  * When retrieving, it must return the top 3 (or fewer) URLs containing the given keyword(s).
* Given a page, it scans the page for keywords, and add that page's URL to the set of URLs associated with each keyword on Redis.
* Processes a page in **O(W)**, where W is the number of words on that page. What is the best O(W) you can implement?
* Queries for pages containg keywords in **O(N)**, where N is the number of relevant URLs.
* Saves the indexing data of a page to Redis in **O(T)**, where T is the number of unique terms on that page.
* A test class that verifies your Indexer's functionality.


### SearchTool Requirements
* Supports union, intersection, and difference operators on search terms.
  * **Union** e.g. given, "arrays OR lists" returns 3 pages containing either of those keywords
  * **Intersection** e.g. given "arrays AND lists", returns 3 pages containing both keywords
  * **Difference** e.g. given "arrays NOT lists", returns 3 pages containing the first keyword but not the second
* Sorts results by relevance (how do you determine relevance? - this can be ask simple as word frequency).
* A test class that verifies your operators and sorting functionalities.
