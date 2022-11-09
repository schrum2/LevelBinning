import sys
import os
import pandas
from functools import reduce
import matplotlib.pyplot as plt

def plot_column(avg_data, col):
    global titles
    # Restricted counts
    df = pandas.DataFrame({
    'FullDirect2GAN': avg_data["FullDirect2GAN"][col],
    'RestrictedDirect2GAN': avg_data["RestrictedDirect2GAN"][col]
    }, index=avg_data["FullDirect2GAN"][0])
    df.plot.line()
    plt.title(titles[col])
    plt.show()

if __name__ == "__main__":

    # python plotAverages.py <experiment dir> <log prefix> <log suffix>

    n = len(sys.argv)
    if n != 4:
        print("Specify a root experiment directory and log file prefix and suffix, as in: ")
        print("python plotAverages.py mariopdsl MarioPDSL- _Fill_log.txt")
        print("python plotAverages.py zeldadungeonswallwaterrooms ZeldaDungeonsWallWaterRooms- _Fill_log.txt")
        exit(1)

    global titles
    titles = dict()
    titles[1] = 'Filled Bins Across Whole Archive'
    titles[2] = 'QD Across Whole Archive'
    titles[3] = 'Maximum Fitness Score Across Whole Archive'
    titles[4] = 'Number of Discarded Individuals'
    titles[5] = 'Filled Bins in the Restricted Region'
    titles[6] = 'QD in the Restricted Region'
    titles[7] = 'Maximum Fitness Score in the Restricted Region'


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



    # Restricted counts
    #df = pandas.DataFrame({
    #'FullDirect2GAN-Full': avg_data["FullDirect2GAN"][3],
    #'FullDirect2GAN-Restricted': avg_data["FullDirect2GAN"][7],
    #'RestrictedDirect2GAN': avg_data["RestrictedDirect2GAN"][3]
    #}, index=avg_data["FullDirect2GAN"][0])
    #df.plot.line()
    #plt.show()


    # TODO: Figure out Standard Dev and Std Err so we can plot error bars

    # Plot each possible average
    for i in range(1,8):
        plot_column(avg_data, i) 
    