# 🌳 Trees & Priority Queues: Memory Allocation Comparison

A project developed for the **Data Structures and Files** course at the **School of Electrical and Computer Engineering, Technical University of Crete**. 

This application implements and benchmarks two fundamental tree-based data structures: a Binary Search Tree (BST) and a Priority Queue (Max-Heap). The primary goal of this project is to contrast the performance and architectural differences between using **contiguous array-based memory** versus **dynamic memory allocation** (pointers).

---

## 🚀 Features & Architecture

### 1️⃣ Binary Search Tree (BST)
Implemented a standard BST supporting random key `Insertion`, `Search`, and `Deletion`. 
* **Dynamic Implementation:** Uses standard dynamically allocated nodes with `info`, `left`, and `right` pointers.
* **Array Implementation:** Simulates a linked structure using a static `Nx3` integer array. 
  * Each row represents a node.
  * The columns simulate the `info`, `left`, and `right` fields.
  * **Custom Memory Management:** The `right` column cleverly serves a dual purpose: it points to the right child, but also acts as an `AVAIL` stack to manage the list of free/available array slots. Custom `getnode()` and `free_node()` methods manage this internal stack during insertions and deletions.

### 2️⃣ Priority Queue (Max-Heap)
Implemented a Max-Heap supporting random key `Insertion`, `Deletion (Extract-Max)`, and `Heap Construction`.



* **Array Implementation:** Uses the standard implicit, complete binary tree array representation (where children of node `i` are at `2i` and `2i + 1`).
* **Dynamic Implementation:** A highly custom dynamic tree approach.
  * Requires explicit pointers for `parent`, `left`, `right`, and a pointer tracking the last inserted element to maintain the complete binary tree property.
  * **Construction Modes:** Supports building the heap by inserting elements *one by one*, as well as a bottom-up *all-at-once* construction method (using a list to track subtrees and enforce the heap property).

---

## 📊 Performance Metrics & Benchmarking

The program processes large datasets (up to 1,000,000 keys) to thoroughly evaluate the time complexity and operational cost of both memory paradigms. It automatically generates tables comparing:

**For the Binary Search Tree:**
* Mean number of comparisons per insertion (across 1,000,000 insertions).
* Total elapsed time for 1,000,000 insertions.
* Mean number of comparisons per deletion (across 100 specific deletions).
* Total elapsed time for 100 deletions.

**For the Priority Queue:**
* Total time to construct the heap all at once vs. one by one.
* Mean comparisons per insertion (across 1,000,000 insertions).
* Mean comparisons per deletion (across 100 extract-max operations).
* Total time for 100 deletions.

---

## 💻 Technical Details & Execution
* **Environment:** Cross-platform compilation supported for Linux and Windows.
* **Time Tracking:** Execution times are calculated natively in nanoseconds for high precision.
* **Documentation:** Accompanied by a detailed technical report analyzing the benchmarking results, justifying why certain allocation methods outperform others in specific scenarios, and explaining the mechanics behind the dynamic Priority Queue.
