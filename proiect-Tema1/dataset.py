import pandas as pd
import matplotlib.pyplot as plt

file_path = './dataset.csv'
data = pd.read_csv(file_path)

for column in data.columns:
    print(column)
    try:
        numeric_values = pd.to_numeric(data[column], errors='coerce')

        if numeric_values.notnull().any():
            mean_value = numeric_values.mean()
            print("Mean =", mean_value)

            median_value = numeric_values.median()
            print("Median =", median_value)

            plt.figure(figsize=(10, 6))
            plt.hist(numeric_values, bins=20, color='blue', alpha=0.7)
            plt.title(f'{column}')
            plt.xlabel(column)
            plt.ylabel('Frequency')
        else:
            print("Column does not contain numeric values")

    except ValueError:
        print("Conversion to numeric failed for column")
plt.show()