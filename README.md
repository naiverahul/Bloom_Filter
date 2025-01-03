# **Bloom Filter Algorithm**

## **Overview**
A **Bloom Filter** is a space-efficient **probabilistic data structure** used to test whether an element is a member of a set. It can yield **false positives** but never **false negatives**:
- **False Positive**: Indicates an element might be in the set when it is not.
- **False Negative**: Never occurs; an element truly in the set will always be detected.

### **Key Properties**
1. **Space-efficient**: Requires less memory than traditional data structures.
2. **Probabilistic**: Guarantees no false negatives but allows false positives.
3. **Applications**: Widely used in caching, database query optimization, and distributed systems.

---

## **How It Works**
1. **Bit Array**: Maintains a bit array of size \( m \), initially set to `0`.
2. **Hash Functions**: Uses \( k \) independent hash functions to map elements to indices in the bit array.
3. **Adding Elements**:
   - For an element, all \( k \) hash functions compute indices in the bit array.
   - These indices are set to `1`.
4. **Checking Membership**:
   - Hash the element using the same \( k \) functions.
   - If all corresponding indices in the bit array are `1`, the element *might* be in the set.
   - If any index is `0`, the element is **definitely not in the set**.

---

## **Mathematics**
### **False Positive Probability**
The probability of a **false positive** (\( P \)) is approximately:
\[
P \approx \left(1 - e^{-\frac{kn}{m}}\right)^k
\]

Where:
- \( n \): Number of elements added.
- \( m \): Size of the bit array.
- \( k \): Number of hash functions.

### **Optimal Number of Hash Functions**
The optimal number of hash functions (\( k \)) to minimize \( P \) is:
\[
k = \left(\frac{m}{n}\right) \ln(2)
\]

### **Bit Array Size**
To achieve a desired false positive probability (\( P \)), the required size of the bit array (\( m \)) is:
\[
m = -\frac{n \ln(P)}{(\ln(2))^2}
\]

---

## **Example**
### Parameters:
- Desired false positive probability: \( P = 0.05 \)
- Number of elements: \( n = 1000 \)

#### **Calculate Bit Array Size (\( m \))**:
\[
m = -\frac{1000 \ln(0.05)}{(\ln(2))^2} \approx 6235
\]

#### **Calculate Optimal Hash Functions (\( k \))**:
\[
k = \left(\frac{6235}{1000}\right) \ln(2) \approx 4
\]

### Result:
- **Bit array size (\( m \))**: 6235 bits
- **Number of hash functions (\( k \))**: 4

---

## **Applications**
1. **Databases**: Quickly check if an item exists before querying.
2. **Distributed Systems**: Reduce unnecessary communication overhead.
3. **Web Caching**: Efficiently verify if a URL/object is cached.

---

## **Advantages**
- Space-efficient and compact.
- Fast insertion and lookup.
- No false negatives.

## **Disadvantages**
- False positives are possible.
- No deletion of elements unless using variants like Counting Bloom Filters.

---

## **References**
- Bloom, Burton H. "Space/Time Trade-offs in Hash Coding with Allowable Errors." *Communications of the ACM*, 1970.
- [Wikipedia: Bloom Filter](https://en.wikipedia.org/wiki/Bloom_filter)
