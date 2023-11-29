import numpy as np
import state


actiuni = ["up", "down", "right", "left"]


class Qmap:
    def __init__(self):
        self.qmap = [[{'up': 0, 'down': 0, 'right': 0, 'left': 0} for _ in range(10)] for _ in range(7)]
        self.learning_rate = 0.1
        self.discount_factor = 0.1
        self.nr_episoade = 100

    def learn(self):
        while self.nr_episoade:
            s = state.State(i=3, j=0)
            while s.cell != (3, 7):
                maxim = -9999
                for a in actiuni:
                    next_pos = s.act[a]
                    reward = state.State(next_pos[0], next_pos[1]).reward



