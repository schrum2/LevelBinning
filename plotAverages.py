import sys
import os
import pandas
from functools import reduce
import matplotlib.pyplot as plt

# python plotAverages.py <experiment dir> <log prefix> <log suffix>

n = len(sys.argv)
if n != 4:
    print("Specify a root experiment directory and log file prefix and suffix, as in: ")
    print("python plotAverages.py mariopdsl MarioPDSL- _Fill_log.txt")
    exit(1)

directory = sys.argv[1]
log_prefix = sys.argv[2]
log_suffix = sys.argv[3]
entries = os.listdir(directory)
# For example: FullDirect2GAN0, FullDirect2GAN1, ..., FullDirect2GAN9, RestrictedDirect2GAN0, RestrictedDirect2GAN1, ..., RestrictedDirect2GAN9

all_data = dict()
prefixes = set()

min_num_rows = float("inf")

for dir in entries:
    name_len = len(dir)
    index = name_len - 1
    while dir[index].isnumeric():
        index -= 1

    # Index is last index before the numeric portion
    prefix = dir[0:(index+1)]
    prefixes.add(prefix)
    experiment_num = int(dir[(index+1):name_len])
    if not prefix in all_data:
        all_data[prefix] = dict()

    #print(prefix, experiment_num)
    #input()

    complete_file_path = f"{directory}/{dir}/{log_prefix}{dir}{log_suffix}"

    all_data[prefix][experiment_num] = pandas.read_csv(complete_file_path, header=None, sep='\t', engine='python') # lineterminator='\r')
    num_rows = all_data[prefix][experiment_num].shape[0]
    min_num_rows = min(min_num_rows,num_rows)

    #print(all_data[prefix][experiment_num])

    #with open(complete_file_path) as log:
    #    for line in log:
    #        values = map(float,line.split())
    #        col = 0
    #        all_data[prefix][experiment_num]["col"+col] = list()
    #        for v in values:

#print(min_num_rows)  
avg_data = dict()

for p in prefixes:
    frame_list = []
    for num in all_data[p]:
        if all_data[p][num].shape[0] > min_num_rows:
            # drop a row since it is one longer
            all_data[p][num].drop(all_data[p][num].tail(1).index,inplace=True)
        
        #print(all_data[p][num])
        #input()

        frame_list.append(all_data[p][num])

    #print(frame_list)
    #input()
    sum_frame = reduce(lambda a, b: a.add(b, fill_value=0), frame_list)
    #print(sum_frame)
    #input()
    avg_frame = sum_frame.div(len(all_data[p]))
    #print(avg_frame)
    #input()
    avg_data[p] = avg_frame

    # TODO: Plot all lines together with column 0 as the x-axis, and each other one on y-axis
    print("Plot")
    avg_frame.plot.line(0,1)
    plt.show()
    avg_frame.plot.line(0,2)
    plt.show()
    input()
