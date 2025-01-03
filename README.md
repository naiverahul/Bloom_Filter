# **Bloom Filter Algorithm**

## **Overview**
A **Bloom Filter** is a space-efficient **probabilistic data structure** used to test whether an element is a member of a set. It can yield **false positives** but never **false negatives**.  
- **False Positive**: The filter may incorrectly indicate that an element is in the set when it is not.
- **False Negative**: The filter will never fail to indicate that an element is in the set when it actually is.

### **Key Properties**
1. **Space-efficient**: Saves memory compared to traditional data structures.
2. **Probabilistic**: Can provide incorrect membership results but guarantees no false negatives.
3. **Application**: Used in caching, database query optimization, and distributed systems.

---

## **How It Works**
1. **Bit Array**: The Bloom filter maintains a bit array of size \( m \), initially all set to 0.
2. **Hash Functions**: It uses \( k \) independent hash functions to map elements to indices in the bit array.
3. **Adding Elements**:
   - For an element, all \( k \) hash functions compute indices in the bit array.
   - These indices are set to 1.
4. **Checking Membership**:
   - An element is checked by hashing it with the same \( k \) hash functions.
   - If all corresponding indices in the bit array are 1, the filter may indicate that the element is in the set.
   - If any index is 0, the element is **definitely not in the set**.

---

## **Probabilistic Mathematics**
The probability of a **false positive** (\( P \)) depends on:
- \( n \): Number of elements added to the Bloom filter.
- \( m \): Size of the bit array.
- \( k \): Number of hash functions.

### **False Positive Probability**
The probability of a false positive can be approximated by:
\[
P \approx \left(1 - e^{-\frac{kn}{m}}\right)^k
\]

### **Optimal Number of Hash Functions**
To minimize the false positive probability, the optimal number of hash functions is:
\[
k = \left(\frac{m}{n}\right) \ln(2)
\]

### **Size of the Bit Array**
To achieve a desired false positive probability (\( P \)), the required size of the bit array is:
\[
m = -\frac{n \ln(P)}{(\ln(2))^2}
\]

---

## **Example**
### Parameters:
- Desired false positive probability: \( P = 0.05 \)
- Number of elements: \( n = 1000 \)

#### 1. **Calculate the Size of the Bit Array (\( m \))**
\[
m = -\frac{1000 \ln(0.05)}{(\ln(2))^2} \approx 6235
\]

#### 2. **Calculate the Optimal Number of Hash Functions (\( k \))**
\[
k = \left(\frac{6235}{1000}\right) \ln(2) \approx 4
\]

### Result:
- **Bit array size (\( m \))**: 6235 bits
- **Number of hash functions (\( k \))**: 4

---

## **Applications**
1. **Databases**:
   - Quickly check if an item exists before querying the database.
2. **Distributed Systems**:
   - Reduce communication overhead by filtering unnecessary queries.
3. **Web Caching**:
   - Efficiently check if a URL or object is already cached.

---

## **Advantages**
- Compact and space-efficient.
- Fast insertion and query operations.
- No false negatives.

## **Disadvantages**
- False positives are possible.
- No mechanism to remove elements once added (unless combined with techniques like counting Bloom filters).

---

## **References**
- Bloom, Burton H. “Space/Time Trade-offs in Hash Coding with Allowable Errors.” *Communications of the ACM*, 1970.
- [Wikipedia - Bloom Filter](https://en.wikipedia.org/wiki/Bloom_filter)
