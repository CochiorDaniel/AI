import numpy as np
import state

actiuni = ["up", "down", "right", "left"]


class Qmap:
    def __init__(self):
        self.qmap = [[{'up': 0, 'down': 0, 'right': 0, 'left': 0} for _ in range(10)] for _ in range(7)]
        self.learning_rate = 0.1
        self.discount_factor = 0.1
        self.nr_episoade = 100

    def learn(self, s):
        while self.nr_episoade:
            while s.cell != (3, 7):
                maxim = -9999
                directie = ""
                state2 = s
                for a in actiuni:
                    next_pos = s.act[a]
                    reward = state.State(next_pos[0], next_pos[1]).reward
                    if reward >= maxim:
                        maxim = reward
                        state2 = state.State(i=next_pos[0], j=next_pos[1])
                        directie = a
                self.qmap[state2.cell[0]][state2.cell[1]][directie] += self.learning_rate * (
                        maxim + self.discount_factor * self.learn(state2) -
                        self.qmap[state2.cell[0]][state2.cell[1]][directie])
